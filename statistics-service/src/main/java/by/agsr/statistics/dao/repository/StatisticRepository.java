package by.agsr.statistics.dao.repository;

import by.agsr.statistics.dao.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StatisticRepository extends JpaRepository<Statistic, Long>, JpaSpecificationExecutor<Statistic> {
}
