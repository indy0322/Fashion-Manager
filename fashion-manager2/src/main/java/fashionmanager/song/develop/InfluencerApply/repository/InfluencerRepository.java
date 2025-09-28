package fashionmanager.song.develop.InfluencerApply.repository;

import fashionmanager.song.develop.InfluencerApply.aggregate.InfluencerApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfluencerRepository extends JpaRepository<InfluencerApplyEntity, Integer> {
}

