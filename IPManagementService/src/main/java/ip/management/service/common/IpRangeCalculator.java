package ip.management.service.common;

import java.util.Comparator;

public class IpRangeCalculator<T> {

	private T lower;
	private T upper;
	private Comparator<T> comparator;

	public IpRangeCalculator(T lower, T upper, Comparator<T> comparator) {
		if (comparator.compare(lower, upper) <= 0) {
			this.lower = lower;
			this.upper = upper;
		} else {
			this.lower = upper;
			this.upper = lower;
		}
		this.comparator = comparator;
	}

	public boolean contains(T element) {
		return comparator.compare(lower, element) <= 0 && comparator.compare(upper, element) >= 0;
	}
}
