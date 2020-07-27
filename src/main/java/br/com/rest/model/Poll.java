package br.com.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "poll")
public class Poll {

	@Id
	@Column(name = "poll_id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long poll_id;

	@Column(length = 255, nullable = false)
	private String poll_description;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "poll")
	private List<Option> options = new ArrayList<Option>();

	public Long getPoll_id() {
		return poll_id;
	}

	public void setPoll_id(Long poll_id) {
		this.poll_id = poll_id;
	}

	public String getPoll_description() {
		return poll_description;
	}

	public void setPoll_description(String poll_description) {
		this.poll_description = poll_description;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((poll_id == null) ? 0 : poll_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poll other = (Poll) obj;
		if (poll_id == null) {
			if (other.poll_id != null)
				return false;
		} else if (!poll_id.equals(other.poll_id))
			return false;
		return true;
	}

}
