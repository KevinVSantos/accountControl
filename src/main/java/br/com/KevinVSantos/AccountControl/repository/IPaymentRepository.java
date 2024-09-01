package br.com.KevinVSantos.AccountControl.repository;

import br.com.KevinVSantos.AccountControl.domain.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment, Long> {
}
