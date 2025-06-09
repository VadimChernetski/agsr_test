package by.agsr.statistics.specification;

import by.agsr.statistics.dao.entity.Statistic;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

@UtilityClass
public class StatisticSpecifications {

    public static Specification<Statistic> hasDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return (Root<Statistic> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (startDate == null && endDate == null) {
                return cb.conjunction();
            }
            if (startDate == null) {
                return cb.lessThanOrEqualTo(root.get("date"), endDate);
            }
            if (endDate == null) {
                return cb.greaterThanOrEqualTo(root.get("date"), startDate);
            }
            return cb.between(root.get("date"), startDate, endDate);
        };
    }
}
