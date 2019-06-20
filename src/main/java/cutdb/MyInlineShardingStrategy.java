package cutdb;

import io.shardingjdbc.core.api.algorithm.sharding.ShardingValue;
import io.shardingjdbc.core.routing.strategy.ShardingStrategy;

import java.util.Arrays;
import java.util.Collection;

public class MyInlineShardingStrategy implements ShardingStrategy {
    @Override
    public Collection<String> getShardingColumns() {
        return Arrays.asList("org");
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        return null;
    }
}
