package io.github.arch2be.orderapprovalservice.framework.out.db;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface OrderEntityRepository extends JpaRepository<OrderEntity, UUID> {
}
