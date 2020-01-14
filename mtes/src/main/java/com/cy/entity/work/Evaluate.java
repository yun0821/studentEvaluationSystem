package com.cy.entity.work;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 评价表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "evaluate", catalog = "cy")
public class Evaluate implements Serializable{

     private static final long serialVersionUID = 1L;

          /**
       *评教表
       */
	private Integer id;
		          /**
       *关联content表的id
       */
	private Integer contentId;
	                  /**
       *评价等级关联字典表的score_level
       */
	private String score;
	                  /**
       *
       */
	private Integer courseId;
	                  /**
       *
       */
	private Integer teacherId;
	                  /**
       *显示顺序
       */
	private String showSort;
	             

        public Evaluate() {

	}

	public Evaluate(Integer id,Integer contentId,String score,Integer courseId,Integer teacherId,String showSort) {
	 	    this.id = id;
         	    this.contentId = contentId;
         	    this.score = score;
         	    this.courseId = courseId;
         	    this.teacherId = teacherId;
         	    this.showSort = showSort;
         	}


                  @Id
                    @GeneratedValue(strategy = GenerationType.IDENTITY)
              @Column(name = "id",   nullable = false )
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id =id;
	}
                        @Column(name = "content_id",   nullable = false )
	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId =contentId;
	}
                        @Column(name = "score",   nullable = false ,  length = 10 )
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score =score;
	}
                        @Column(name = "course_id",   nullable = false )
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId =courseId;
	}
                        @Column(name = "teacher_id",   nullable = false )
	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId =teacherId;
	}
                        @Column(name = "show_sort",    nullable = true ,  length = 50 )
	public String getShowSort() {
		return showSort;
	}

	public void setShowSort(String showSort) {
		this.showSort =showSort;
	}
    
      @Override
     public String toString() {
      		     	             	             	             	             	                    return "Evaluate [id = "+id+",contentId = "+contentId+",score = "+score+",courseId = "+courseId+",teacherId = "+teacherId+",showSort = "+showSort+"]";
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Evaluate)) {
            return false;
        }
        Evaluate that = (Evaluate) o;
        boolean isEqual=true;
                                    isEqual=Objects.equals(getId(),that.getId());
                                                isEqual=isEqual&&Objects.equals(getContentId(),that.getContentId());
                                                isEqual=isEqual&&Objects.equals(getScore(),that.getScore());
                                                isEqual=isEqual&&Objects.equals(getCourseId(),that.getCourseId());
                                                isEqual=isEqual&&Objects.equals(getTeacherId(),that.getTeacherId());
                                                isEqual=isEqual&&Objects.equals(getShowSort(),that.getShowSort());
                            return isEqual;
    }

    @Override
    public int hashCode() {
                                                                                                                                                                                                                        return Objects.hash(getId(),getContentId(),getScore(),getCourseId(),getTeacherId(),getShowSort());
    }

}
