package ip.management.service.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ip.management.service.enums.IpResourceState;
import ip.management.service.exception.IpAddressOutOfRangeException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class IpAddressResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6151050930748499582L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "ip_value")
	private String value;

	@Enumerated(EnumType.STRING)
	private IpResourceState status;

	@OneToOne
	@JoinColumn(name = "ip_pool_id")
	private IpPoolResource ipPool;

	public IpAddressResource(IpPoolResource ipPool, String ipAddressValue, IpResourceState ipStatus) {
		Objects.requireNonNull(ipPool, "IpPool's information cannot be null!");
		Objects.requireNonNull(ipAddressValue, "IP Address Value cannot be null!");
		this.ipPool = ipPool;
		if (!(this.ipPool.validateIpAvailabeInPool(ipAddressValue))) {
			throw new IpAddressOutOfRangeException("Given IP Address is not availabe in range!");
		}
		ipPool.take();
		this.value = ipAddressValue;
		this.status = ipStatus;

	}

	public void returnIpAddress() {
		this.status = IpResourceState.FREE;
		this.ipPool.returnIpAddress();
	}

}
