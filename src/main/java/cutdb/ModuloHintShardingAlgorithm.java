package cutdb;

import io.shardingjdbc.core.api.algorithm.sharding.ShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.hint.HintShardingAlgorithm;

import java.util.Collection;

/***
 * 强制路由
 */
public class ModuloHintShardingAlgorithm implements HintShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ShardingValue shardingValue) {
        return null;
    }
}
