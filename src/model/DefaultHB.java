package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class DefaultHB {
	Session session;
	SessionFactory factory;
	public DefaultHB(String className){
		 factory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(className.getClass()).buildSessionFactory();
		 session=factory.openSession();
	}
	
	public void beginTransaction(){
		session.beginTransaction();
	}
	
	public void saveObject(Object object){
		session.save(object);
	}
	
	public void deleteObject(Object object){
		session.delete(object);
	}
	
	public void closeTransaction(){
		session.close();
		factory.close();
	}
	public void commitTransaction(){
		session.getTransaction().commit();
	}
	
	public Object getSpecificEntity(Object entity, String identity){
		return session.get(entity.getClass(),identity);
	}
	
	public List getEntity(String entity){
		List list=session.createQuery("from "+entity).getResultList();
		return list;
	}
	//overload getEntity to get entity if there is criteria
	public List getEntity(String entity, String criteria){
		List list=session.createQuery("from "+entity+" e "+criteria).getResultList();
		return list;
	}
	public List getEntity(String entity,int firstResult, int maxResult){
		List list=new ArrayList(); 
		Query query=session.createQuery("from "+entity);
		 query.setFirstResult(firstResult);
		 query.setMaxResults(maxResult);
		 list=query.getResultList();
		 return list;
	}
}
