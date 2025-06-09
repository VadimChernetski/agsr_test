package by.agsr.monitor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Tuple<T1, T2> {

    private T1 t1;
    private T2 t2;

}
