package com.proinsight.domain.model;

import com.proinsight.domain.enums.OrderStatus;
import com.proinsight.domain.event.CancelOrderEvent;
import com.proinsight.domain.event.ConfirmOrderEvent;
import com.proinsight.domain.event.PreparingOrderEvent;
import com.proinsight.domain.event.ReceiveOrderEvent;
import com.proinsight.domain.exception.BusinessException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "`order`")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Order extends AbstractAggregateRoot<Order> {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long code;

	private BigDecimal total;

	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.CREATED;

	@CreationTimestamp
	private OffsetDateTime receptionDate;

	private OffsetDateTime cancelDate;
	private OffsetDateTime confirmationDate;
	private OffsetDateTime preparationDate;
	private OffsetDateTime finalizationDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private PaymentMethod paymentMethod;

	@ManyToOne
	@JoinColumn(nullable = false)
	private SnackShop snackShop;

	@ManyToOne
	@JoinColumn(name = "user_client_id", nullable = false)
	private User client;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> items = new ArrayList<>();

	public void calculateTotal() {
		getItems().forEach(OrderItem::recalculateTotalPrice);

		this.total = getItems().stream()
			.map(item -> item.getTotalPrice())
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public void receive() {
		setStatus(OrderStatus.RECEIVED);
		registerEvent(new ReceiveOrderEvent(this));
	}

	public void confirm() {
		setStatus(OrderStatus.CONFIRMED);
		setConfirmationDate(OffsetDateTime.now());
		registerEvent(new ConfirmOrderEvent(this));
	}

	public void prepare() {
		setStatus(OrderStatus.IN_PROGRESS);
		setPreparationDate(OffsetDateTime.now());
		registerEvent(new PreparingOrderEvent(this));
	}

	public void ready() {
		setStatus(OrderStatus.READY);
		setConfirmationDate(OffsetDateTime.now());
	}

	public void delivery() {
		setStatus(OrderStatus.DELIVERED);
		setFinalizationDate(OffsetDateTime.now());
	}

	public void cancel() {
		setStatus(OrderStatus.CANCELED);
		setCancelDate(OffsetDateTime.now());

		registerEvent(new CancelOrderEvent(this));
	}

	private void setStatus(OrderStatus newStatus) {
		if (getStatus().cannotChangeTo(newStatus)) {
			throw new BusinessException(
					String.format("Status do pedido %s não pode ser alterado de %s para %s",
							getCode(), getStatus().getDescription(),
							newStatus.getDescription()));
		}

		this.status = newStatus;
	}
	public boolean canBeCanceled() {
		return getStatus().canChangeTo(OrderStatus.CANCELED);
	}
	public boolean canBeConfirmed() {
		return getStatus().canChangeTo(OrderStatus.IN_PROGRESS);
	}

	public boolean canBeReady() {
		return getStatus().canChangeTo(OrderStatus.READY);
	}

	public boolean canBeCompleted() {
		return getStatus().canChangeTo(OrderStatus.DELIVERED);
	}

}
