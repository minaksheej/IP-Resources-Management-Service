package ip.management.service.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class IpPoolResource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "lowerBound")
	private String lowerBound;

	@Column(name = "upperBound")
	private String upperBound;

	@Column(name = "total_Capacity")
	private Long totalCapacity;

	@Column(name = "used_Capacity")
	private Long usedCapacity;

	private void validateBounds(String lowerBound, String upperBound) {
		Objects.requireNonNull(lowerBound, "IP's lowerBound cannot be null!");
		Objects.requireNonNull(upperBound, "IP's upperBound cannot be null!");
	}

	public IpPoolResource(String description, String lowerBound, String upperBound) {
		validateBounds(lowerBound, upperBound);
		this.description = description;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.usedCapacity = 0L;
		this.totalCapacity = calculateTotalCapacity(lowerBound, upperBound);
	}

	private Long calculateTotalCapacity(String lowerBound, String upperBound) {
		Long capacity = 1L;
		String[] lowerBounddArray = lowerBound.split("\\.");
		String[] upperBounddArray = upperBound.split("\\.");
		for (int i = 0; i < upperBounddArray.length; i++) {
			if (upperBounddArray[i] != lowerBounddArray[i]) {
				capacity = capacity
						* ((Integer.parseInt(upperBounddArray[i]) - Integer.parseInt(lowerBounddArray[i])) + 1);
			}
		}
		return capacity;
	}
}
