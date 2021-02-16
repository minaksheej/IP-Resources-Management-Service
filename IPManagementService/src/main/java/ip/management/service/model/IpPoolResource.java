package ip.management.service.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.net.InetAddresses;

import ip.management.service.common.IpRangeCalculator;
import ip.management.service.enums.IpAddressComparator;
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
		String[] lowerBounddArray = convertIpStringToArray(lowerBound);
		String[] upperBounddArray = convertIpStringToArray(upperBound);
		for (int i = 0; i < upperBounddArray.length; i++) {
			if (upperBounddArray[i] != lowerBounddArray[i]) {
				capacity = capacity
						* ((Integer.parseInt(upperBounddArray[i]) - Integer.parseInt(lowerBounddArray[i])) + 1);
			}
		}
		return capacity;
	}

	private String[] convertIpStringToArray(String ipAddress) {
		String[] ipBounddArray = ipAddress.split("\\.");
		return ipBounddArray;
	}

	public List<String> generateIps(long noOfIpAddresses) {
		List<String> generatedIpList = new ArrayList<>();
		for (int ipNumber = 0; ipNumber < noOfIpAddresses; ipNumber++) {
			InetAddress addresses = InetAddresses.forString(this.lowerBound);
			generatedIpList.add(InetAddresses.toAddrString(InetAddresses.increment(addresses)));
		}

		return generatedIpList;

	}

	public boolean validateIpAvailabeInPool(String ipAddress) {
		return new IpRangeCalculator<InetAddress>(InetAddresses.forString(this.lowerBound),
				InetAddresses.forString(this.upperBound), IpAddressComparator.INSTANCE)
						.contains(InetAddresses.forString(ipAddress));

	}

	public void take() {
		this.lowerBound = InetAddresses.toAddrString(InetAddresses.increment(InetAddresses.forString(this.lowerBound)));
		this.usedCapacity=usedCapacity+1;
	}
	
	public void  returnIpAddress() {
		this.lowerBound = InetAddresses.toAddrString(InetAddresses.decrement(InetAddresses.forString(this.lowerBound)));
		this.usedCapacity=usedCapacity-1;

	}
}
