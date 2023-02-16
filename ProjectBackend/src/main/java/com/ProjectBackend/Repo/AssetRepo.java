package com.ProjectBackend.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.ProjectBackend.model.asset.AssetInfo;

@Component
public interface AssetRepo extends MongoRepository<AssetInfo, Integer>
{

}
