package com.model;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class EmployeeOperation {
	@SuppressWarnings({ "unchecked", "removal" })
	public static void main(String[] args)
	{
		SessionFactory sessionFactory=HibernateUtil.getSesFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria criteria=session.createCriteria(Emoloyee.class);
		List<Emoloyee>empList=criteria.list();
		for(Emoloyee emp:empList)
		{
			System.out.println("ID="+emp.getId()+emp.getName()+emp.getSalary());
		}
		criteria=session.createCriteria(Emoloyee.class).add(Restrictions.eq("id", new Integer(2)));
		Emoloyee emp=(Emoloyee) criteria.uniqueResult();
		System.out.println("Name="+emp.getName() );
		empList=session.createCriteria(Emoloyee.class)
				.addOrder(Order.desc("id"))
				.setFirstResult(0)
				.setMaxResults(2)
				.list();
		for(Emoloyee emp4: empList)
		{
			System.out.println("Paginated Employees"+emp4.getId()+ emp4.getName());
		}
		empList=session.createCriteria(Emoloyee.class)
				.add(Restrictions.like("name","%m%"))
				.list();
		for(Emoloyee emp4:empList)
		{
			System.out.println("Employee having 'm' in name::"+emp4.getName());
		}
		Criteria c=session.createCriteria(Emoloyee.class);
		c.setProjection(Projections.rowCount());
		empList=c.add(Restrictions.like("name","%m%")).list();
		System.out.println("Number of Employees with m in name="+empList.getFirst());
		Criteria d=session.createCriteria(Emoloyee.class);
		empList=d.setProjection(Projections.sum("salary")).list();
		System.out.println("Sum fo Salaries="+empList.getFirst());
		tx.commit();
		sessionFactory.close();
		}
}

		
		
		
		
		
	
