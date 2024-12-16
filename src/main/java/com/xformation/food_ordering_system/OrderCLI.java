package com.xformation.food_ordering_system;

import com.xformation.food_ordering_system.dto.AddonDto;
import com.xformation.food_ordering_system.dto.CuisineDto;
import com.xformation.food_ordering_system.dto.MenuItemCategoryDto;
import com.xformation.food_ordering_system.dto.MenuItemDto;
import com.xformation.food_ordering_system.dto.OrderDto;
import com.xformation.food_ordering_system.dto.OrderItemDto;
import com.xformation.food_ordering_system.service.CuisineService;
import com.xformation.food_ordering_system.service.MenuItemService;
import com.xformation.food_ordering_system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Service
public class OrderCLI {
    private static final Pageable DEFAULT_PAGEABLE = Pageable.ofSize(10);
    private static final String DESSERT_CATEGORY_NAME = "Dessert";
    private static final String DRINK_CATEGORY_NAME = "Drink";
    private static final String WELCOME_START_MESSAGE = """
            -------------------------------------------
            Welcome to the Restaurant Ordering System!
            -------------------------------------------
            What'll it be?
            Enter your choice:
            1. Lunch
            2. Drink""";
    private static final String ORDER_CANCELLED_MESSAGE = "Order cancelled.";
    private static final String THANK_YOU_FOR_ORDER_MESSAGE = "Thank you for your order!";
    private static final String INVALID_INPUT_MESSAGE = "Invalid input. Please enter a valid number.";
    private static final String INVALID_INPUT_WITH_EXIT_MESSAGE = "Invalid input. Please enter a valid number or type 'exit' to cancel";
    private static final String CHOOSE_ADDONS_MESSAGE = "Choose drink-specific addons: ";
    private static final String CHOOSE_DESSERT_MESSAGE = "Choose a dessert:";
    private static final String ADDON_ALREADY_CHOSEN_MESSAGE = "Addon already chosen. Please choose a different addon.";
    private static final String ORDER_SUMMARY_MESSAGE = "Order summary:";
    private static final String ORDER_CONFIRMATION_MESSAGE = "Confirm order? (1 - yes, 2 - no)";
    private static final String CHOOSE_CUISINE_MESSAGE = "Choose a cuisine of the main course:";
    private static final String CHOOSE_DRINK_MESSAGE = "Choose a drink:";
    private static final String ASK_FOR_DRINK_MESSAGE = "Do you want a drink? (1 - yes, 2 - no)";

    private final OrderService orderService;
    private final MenuItemService menuItemService;
    private final CuisineService cuisineService;
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println(WELCOME_START_MESSAGE);
        var choice = getValidIntInput(1, 2);
        List<OrderItemDto> cart = new ArrayList<>();

        if (choice == 1) {
            var mainCourse = chooseMainCourse();
            var dessert = chooseDessert();
            cart.add(OrderItemDto.builder().menuItem(mainCourse).build());
            cart.add(OrderItemDto.builder().menuItem(dessert).build());
            if (askForDrink()) {
                var chosenDrink = chooseDrink();
                var chosenAddons = chooseDrinkAddons(chosenDrink.addons());
                cart.add(OrderItemDto.builder().menuItem(chosenDrink).addons(chosenAddons).build());
            }
        } else if (choice == 2) {
            var chosenDrink = chooseDrink();
            var chosenAddons = chooseDrinkAddons(chosenDrink.addons());
            cart.add(OrderItemDto.builder().menuItem(chosenDrink).addons(chosenAddons).build());
        }

        displayOrderSummary(cart);
        if (confirmOrder()) {
            orderService.save(OrderDto.builder().orderItems(cart).build());
            System.out.println(THANK_YOU_FOR_ORDER_MESSAGE);
        } else {
            System.out.println(ORDER_CANCELLED_MESSAGE);
        }
    }

    private MenuItemDto chooseMainCourse() {
        System.out.println(CHOOSE_CUISINE_MESSAGE);
        var cuisines = cuisineService.findAll(DEFAULT_PAGEABLE);
        displayCuisines(cuisines.getContent());
        var chosenCuisineOrdinal = getValidIntInput(1, cuisines.getContent().size());
        var chosenCuisine = cuisines.getContent().get(chosenCuisineOrdinal - 1);
        var mainCourses = menuItemService.findAll(
                Example.of(MenuItemDto.builder().cuisine(chosenCuisine).build()), DEFAULT_PAGEABLE);
        displayMenuItems(mainCourses.getContent());
        var chosenMenuItemOrdinal = getValidIntInput(1, mainCourses.getContent().size());
        return mainCourses.getContent().get(chosenMenuItemOrdinal - 1);
    }

    private MenuItemDto chooseDessert() {
        System.out.println(CHOOSE_DESSERT_MESSAGE);
        var desserts = menuItemService.findAll(
                Example.of(MenuItemDto.builder()
                        .category(MenuItemCategoryDto.builder().name(DESSERT_CATEGORY_NAME).build())
                        .build(), ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues()),
                DEFAULT_PAGEABLE);
        displayMenuItems(desserts.getContent());
        var chosenDessertOrdinal = getValidIntInput(1, desserts.getContent().size());
        return desserts.getContent().get(chosenDessertOrdinal - 1);
    }

    private boolean askForDrink() {
        System.out.println(ASK_FOR_DRINK_MESSAGE);
        return getValidIntInput(1, 2) == 1;
    }

    private MenuItemDto chooseDrink() {
        System.out.println(CHOOSE_DRINK_MESSAGE);
        var drinks = menuItemService.findAll(
                Example.of(MenuItemDto.builder()
                        .category(MenuItemCategoryDto.builder().name(DRINK_CATEGORY_NAME).build())
                        .build()),
                DEFAULT_PAGEABLE);
        displayMenuItems(drinks.getContent());
        var chosenMenuItemOrdinal = getValidIntInput(1, drinks.getContent().size());
        return drinks.getContent().get(chosenMenuItemOrdinal - 1);
    }

    private List<AddonDto> chooseDrinkAddons(List<AddonDto> addons) {
        System.out.println(CHOOSE_ADDONS_MESSAGE);
        displayAddons(addons);

        var chosenAddons = new ArrayList<AddonDto>();
        var chosenAddonOrdinals = new HashSet<Integer>();

        while (true) {
            var chosenAddonOrdinal = getValidIntInputWithTerminate(1, addons.size());
            if (chosenAddonOrdinal == null) {
                break;
            }
            if (chosenAddonOrdinals.contains(chosenAddonOrdinal)) {
                System.out.println(ADDON_ALREADY_CHOSEN_MESSAGE);
            } else {
                chosenAddonOrdinals.add(chosenAddonOrdinal);
                chosenAddons.add(addons.get(chosenAddonOrdinal - 1));
                System.out.println("Addon " + addons.get(chosenAddonOrdinal - 1).name() + " added.");
                System.out.println(CHOOSE_ADDONS_MESSAGE);
                displayAddons(addons);
            }
        }

        return chosenAddons;
    }

    private void displayMenuItems(List<MenuItemDto> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + 1 + ". " + items.get(i).name());
        }
    }

    private void displayCuisines(List<CuisineDto> cuisines) {
        for (int i = 0; i < cuisines.size(); i++) {
            System.out.println(i + 1 + ". " + cuisines.get(i).name());
        }
    }

    private void displayAddons(List<AddonDto> addons) {
        for (int i = 0; i < addons.size(); i++) {
            System.out.println(i + 1 + ". " + addons.get(i).name());
        }
    }

    private void displayOrderSummary(List<OrderItemDto> orderItems) {
        System.out.println(ORDER_SUMMARY_MESSAGE);
        orderItems.forEach(orderItem ->
                System.out.println(orderItem.menuItem().name() + " - " + orderItem.menuItem().price()));
        System.out.println(
                "Total: " + orderItems.stream().mapToDouble(orderItem -> {
                    var itemPrice = orderItem.menuItem().price();
                    var addonsPrice = orderItem.addons().stream().mapToDouble(AddonDto::price).sum();
                    return itemPrice + addonsPrice;
                }).sum());
    }

    private boolean confirmOrder() {
        System.out.println(ORDER_CONFIRMATION_MESSAGE);
        return getValidIntInput(1, 2) == 1;
    }

    private Integer getValidIntInput(int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } else {
                System.out.println(INVALID_INPUT_MESSAGE);
                scanner.next();
            }
        }
    }

    private Integer getValidIntInputWithTerminate(int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max +
                            ". Or type 'exit' to cancel.");
                }
            } else {
                if (scanner.next().equals("exit")) {
                    return null;
                }
                System.out.println(INVALID_INPUT_WITH_EXIT_MESSAGE);
            }
        }
    }

}