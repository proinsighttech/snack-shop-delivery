package com.proinsight.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "snack_shop")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SnackShop {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    private OffsetDateTime registrationDate;

    private Boolean active = Boolean.TRUE;

    @ManyToMany
    @JoinTable(name = "snack_shop_payment_method",
            joinColumns = @JoinColumn(name = "snack_shop_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private Set<PaymentMethod> paymentMethods = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "snack_shop_user_admin",
            joinColumns = @JoinColumn(name = "snack_shop_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> admins = new HashSet<>();

    @OneToMany(mappedBy = "snackShop")
    private List<Product> products = new ArrayList<>();

    public void active() {
        setActive(true);
    }

    public void desactive() {
        setActive(false);
    }


    public boolean removePaymentMethod(PaymentMethod paymentMethod) {
        return getPaymentMethods().remove(paymentMethod);
    }

    public boolean addPaymentMethod(PaymentMethod paymentMethod) {
        return getPaymentMethods().add(paymentMethod);
    }

    public boolean acceptPaymentMethod(PaymentMethod paymentMethod) {
        return getPaymentMethods().contains(paymentMethod);
    }

    public boolean cannotAcceptPaymentMethod(PaymentMethod paymentMethod) {
        return !acceptPaymentMethod(paymentMethod);
    }

    public boolean removeAdmin(User user) {
        return getAdmins().remove(user);
    }

    public boolean addAdmin(User user) {
        return getAdmins().add(user);
    }

}