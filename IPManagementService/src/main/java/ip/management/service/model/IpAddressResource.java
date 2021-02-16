package ip.management.service.model;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class IpAddressResource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ip_value")
	private String value;

	@Enumerated(EnumType.STRING)
	private IpResourceState status;

	@OneToOne
	@JoinColumn(name = "ip_pool_id")
	private IpPoolResource ipAddress;

}
