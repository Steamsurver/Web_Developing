package db_adapter;


import java.util.List;
import db_adapter.entities.EGameKey;
import db_adapter.entities.EProduct;
import db_adapter.entities.EUser;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;


public class DB_Adapter {
	
	@PersistenceUnit(unitName = "TradeApp")
    private EntityManagerFactory entityManagerFactory;
   
    @Resource
    private UserTransaction userTransaction;
    
    private EntityManager entityManager;
    
    
    public void registrateNewUser(String login, String password) throws Exception {
    	
    	entityManager = entityManagerFactory.createEntityManager();
    	try {
			userTransaction.begin();
		} catch (NotSupportedException | SystemException e) {
			throw new Exception("Error while JPA operating: " + e.getMessage());
		}
    	
        entityManager.joinTransaction();
        EUser personPersist = new EUser();
	    personPersist.setLogin(login);		 
	    personPersist.setPassword(password);
	    personPersist.setAccessLevel("user");
	    entityManager.persist(personPersist);
	    userTransaction.commit();
    }
    
    
    public EUser authorisateUser(String login, String password) throws Exception {
		entityManager = entityManagerFactory.createEntityManager();
		
		try {
			userTransaction.begin();
		} catch (NotSupportedException | SystemException e) {
			throw new Exception("Error while JPA operating: " + e.getMessage());
		}
		
		entityManager.joinTransaction();
		List<EUser> users = entityManager.createQuery("SELECT u FROM EUser u",EUser.class).getResultList();     
		
		for(EUser user : users) {
			if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
				userTransaction.commit();
				return user;
			}
		}
		userTransaction.commit();
		return null;
    }

    
    public List<EProduct> getFullCatalog() throws Exception {
    	entityManager = entityManagerFactory.createEntityManager();
		
		try {
			userTransaction.begin();
		} catch (NotSupportedException | SystemException e) {
			throw new Exception("Error while JPA operating: " + e.getMessage());
		}
		
		entityManager.joinTransaction();
		List<EProduct> products = entityManager.createQuery("SELECT p FROM EProduct p",EProduct.class).getResultList();    
		userTransaction.commit();
		return products;
    }
    
    
    public boolean checkUserToken(String token) throws Exception {
    	entityManager = entityManagerFactory.createEntityManager();
		
		try {
			userTransaction.begin();
		} catch (NotSupportedException | SystemException e) {
			throw new Exception("Error while JPA operating: " + e.getMessage());
		}
		
		entityManager.joinTransaction();
		List<EUser> users = entityManager.createQuery("SELECT u FROM EUser u",EUser.class).getResultList();      
		
		for(EUser user : users) {
			if(user.getPassword().equals(token)) {
				userTransaction.commit();
				return true;
			}
		}
		userTransaction.commit();
		return false;
    }
    
    
    public String getKeyById(String id) throws Exception {
    	entityManager = entityManagerFactory.createEntityManager();
		
		try {
			userTransaction.begin();
		} catch (NotSupportedException | SystemException e) {
			throw new Exception("Error while JPA operating: " + e.getMessage());
		}
		
		entityManager.joinTransaction();
		List<EGameKey> keys = entityManager.createQuery("SELECT g FROM EGameKey g", EGameKey.class).getResultList();  
		
		for(EGameKey key : keys) {
			if(key.getGameId().toString().equals(id)) {
				userTransaction.commit();
				return key.getGameKey();
			}
		}
		userTransaction.commit();
		return null;
    }
    
    
    public boolean postProduct(String name, String cost, String desc, String img) throws Exception {
    	entityManager = entityManagerFactory.createEntityManager();
		
		try {
			userTransaction.begin();
		} catch (NotSupportedException | SystemException e) {
			throw new Exception("Error while JPA operating: " + e.getMessage());
		}
		
		entityManager.joinTransaction();
		int size = entityManager.createQuery("SELECT p FROM EProduct p",EProduct.class).getResultList().size();   
	
	    EProduct product = new EProduct();
	    size+=1;
	    product.setId(size);
	    product.setGameName(name);
	    product.setCost(Integer.parseInt(cost));
	    product.setDescription(desc);
	    product.setImgSource(img);
	    
		entityManager.persist(product);
		userTransaction.commit();
		return true;
    }
}
