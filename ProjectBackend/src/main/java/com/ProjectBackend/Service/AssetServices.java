package com.ProjectBackend.Service;

import java.util.List;

import com.ProjectBackend.model.asset.AddAssetClassInfo;
import com.ProjectBackend.model.asset.AddAssetInfo;
import com.ProjectBackend.model.asset.AssetClass;
import com.ProjectBackend.model.asset.AssetInfo;

public interface AssetServices {
	public void insertNewAsset(AddAssetInfo asset);
	public void insertNewAssetClass(AddAssetClassInfo asset);
	public AssetClass getAssetClass(String name);
	public List<AssetClass> getAllClasses();
	public List<AssetInfo> getAllInfo();
	
}
