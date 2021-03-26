DROP VIEW amd_weka_instances;
CREATE VIEW amd_weka_instances AS (

  SELECT c.id, c.id_student, c.id_unit, u.id_course, c.downloaded, c.viewed_count, a.days_past_due, t.time, t.grade, t.difficulty  FROM
    amd_student_unit_course c 
  LEFT JOIN 
    amd_student_unit_assignment a 
  ON
    c.id_student = a.id_student
  LEFT JOIN
    amd_student_unit_test t
  ON 
    c.id_student = t.id_student
  LEFT JOIN 
  	amd_unit_course u
  ON
  	c.id_unit = u.id
  WHERE 
    c.id_unit=a.id_unit
  AND
    c.id_unit=t.id_unit
);