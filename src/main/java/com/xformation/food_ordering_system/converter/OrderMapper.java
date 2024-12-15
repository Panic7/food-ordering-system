package com.xformation.food_ordering_system.converter;

import com.xformation.food_ordering_system.dto.OrderDto;
import com.xformation.food_ordering_system.model.Order;
import org.hashids.Hashids;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class OrderMapper implements BaseMapper<Order, OrderDto> {

    private Hashids hashids;

    @Autowired
    public OrderMapper setHashids(Hashids hashids) {
        this.hashids = hashids;
        return this;
    }

    @Mapping(source = "id", target = "publicId", qualifiedByName = "encodeId")
    public abstract OrderDto toDto(Order order);

    @Mapping(source = "publicId", target = "id", qualifiedByName = "decodeId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Order toEntity(OrderDto orderDTO);

    public abstract List<OrderDto> toDtoList(List<Order> orders);

    @Named("encodeId")
    public String encodeId(Integer id) {
        return hashids.encode(id);
    }

    @Named("decodeId")
    public Integer decodeId(String publicId) {
        if (publicId == null) {
            return null;
        }

        return (int) hashids.decode(publicId)[0];
    }
}