package com.github.nicolasholanda.debt.model.mapper;

import com.github.nicolasholanda.debt.model.Customer;
import com.github.nicolasholanda.debt.model.Demand;
import com.github.nicolasholanda.debt.model.DemandItem;
import com.github.nicolasholanda.debt.model.dto.NewDemandDTO;
import com.github.nicolasholanda.debt.model.enuns.DemandStatus;
import com.github.nicolasholanda.debt.model.enuns.PaymentStatus;
import com.github.nicolasholanda.debt.service.AddressService;
import com.github.nicolasholanda.debt.service.ApplicationUserService;
import com.github.nicolasholanda.debt.service.ProductService;
import com.github.nicolasholanda.debt.service.StoreService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static com.github.nicolasholanda.debt.model.enuns.UserType.CUSTOMER;

@Mapper(componentModel = "spring")
public abstract class NewDemandMapper {

    @Autowired
    protected StoreService storeService;

    @Autowired
    protected ProductService productService;

    @Autowired
    protected AddressService addressService;

    @Autowired
    protected ApplicationUserService applicationUserService;

    public Demand toModel(NewDemandDTO dto) {
        Demand demand = new Demand();

        var store = storeService.findById(dto.getStoreId());
        var shipAddress = addressService.findById(dto.getAddressId());
        var customer = applicationUserService.findById(dto.getCustomerId());
        var items = dto.getItems().stream().map(i -> {
            var product = productService.findById(i.getProductId());
            return new DemandItem(product, demand, i.getQuantity(), i.getDiscount());
        }).collect(Collectors.toSet());
        if(!customer.getUserType().equals(CUSTOMER)) {
            throw new IllegalArgumentException("O comprador do pedido não pode ser um usuário vendedor.");
        } else if(!shipAddress.getUser().getId().equals(customer.getId())) {
            throw new IllegalArgumentException("O endereço de entrega do pedido deve ter sido cadastrado pelo comprador.");
        }
        demand.setRequestDate( dto.getRequestDate() );
        demand.setPayment( dto.getPayment() );
        demand.setItems(items);
        demand.setStore(store);
        demand.setShipAddress(shipAddress);
        demand.setCustomer((Customer) customer);
        demand.setStatus(DemandStatus.NEW.getCode());
        demand.getPayment().setDemand(demand);
        demand.getPayment().setStatus(PaymentStatus.PENDING.getCode());

        return demand;
    }
}
