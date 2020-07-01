package com.revature.dtos;

import java.time.LocalDate;

/**
 * DTO - Data Transfer Object Not the entity, but a convenient way to track it
 */
public class BookDto {
	private int id;
	private String title;
	private LocalDate releaseDate;
	private int pageCount;
	private Integer authorId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + id;
		result = prime * result + pageCount;
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		BookDto other = (BookDto) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (id != other.id)
			return false;
		if (pageCount != other.pageCount)
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookDto [id=" + id + ", title=" + title + ", releaseDate=" + releaseDate + ", pageCount=" + pageCount
				+ ", authorId=" + authorId + "]";
	}

	public BookDto(int id, String title, LocalDate releaseDate, int pageCount, Integer authorId) {
		super();
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.pageCount = pageCount;
		this.authorId = authorId;
	}

	public BookDto() {
		super();
	}

}
