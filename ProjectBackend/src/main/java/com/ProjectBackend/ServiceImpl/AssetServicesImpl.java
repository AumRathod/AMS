package com.ProjectBackend.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjectBackend.Repo.AssetClassRepo;
import com.ProjectBackend.Repo.AssetRepo;
import com.ProjectBackend.Service.AssetServices;
import com.ProjectBackend.model.asset.AddAssetClassInfo;
import com.ProjectBackend.model.asset.AddAssetInfo;
import com.ProjectBackend.model.asset.AssetClass;
import com.ProjectBackend.model.asset.AssetInfo;

@Service
public class AssetServicesImpl implements AssetServices {

	@Autowired
	AssetRepo assetRepo;

	@Autowired
	AssetClassRepo classRepo;

	@Autowired
	AssetInfo assetInfo;

	@Override
	public void insertNewAsset(AddAssetInfo asset) {
		AssetInfo ai = new AssetInfo();
		ai.setName(asset.getName());
		ai.setAttributes(asset.getParams());
		assetRepo.save(ai);
	}

	@Override
	public void insertNewAssetClass(AddAssetClassInfo asset) {
		AssetClass as = new AssetClass();
		as.setName(asset.getName());
		as.setKeys(asset.getKeys());
		classRepo.save(as);
	}

	@Override
	public AssetClass getAssetClass(String name) {
		return classRepo.findByName(name);
	}

	@Override
	public List<AssetClass> getAllAssetClasses() {
		return classRepo.findAll();
	}

	@Override
	public List<AssetInfo> getAllAssetsInfo() {
		return assetRepo.findAll();
	}

}