package builder;

import builder.Build.Built;
import db_adapter.DB_Adapter;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import model.interfaces.IProductProcessor;

public class Builder {
	 @Inject @Default
	 private IProductProcessor model;

	 @Inject @Default
	 private DB_Adapter repository;

	 @Produces @Built
	 public IProductProcessor buildModel() {
		model.injectAdapter(repository);
	    return model;
	 } 
}
