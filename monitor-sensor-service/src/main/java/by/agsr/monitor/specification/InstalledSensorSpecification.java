package by.agsr.monitor.specification;

import by.agsr.monitor.dao.entity.InstalledSensor;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class InstalledSensorSpecification {

    public static Specification<InstalledSensor> buildSpecification(String name, String model) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (model != null && !model.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("sensor").get("model")), "%" + model.toLowerCase() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
