package com.alinadev.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Alina Ditu
 */

@Entity
@Table(name = "amd_course", catalog = "elearningdb")
public class Course {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	private int id;

    @Column(name = "name")
	private String name;

    @Column(name = "study_year")
	private Integer studyYear;

    @Column(name = "reference")
    private String reference;

    @Column(name = "due_date")
    private Date dueDate;

	public Course() {

	}

	public Course(String name, Integer studyYear, String reference, Date date) {
		super();
		this.name = name;
		this.studyYear = studyYear ;
        this.reference = reference;
        this.dueDate = date;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Integer getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(Integer studyYear) {
        this.studyYear = studyYear;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
