package cutdb;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.UUID;

@Slf4j
public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        String name = shardingValue.getLogicTableName() + (Math.abs(UUID.fromString(shardingValue.getValue()).getMostSignificantBits()) % 2 + 1);
        log.debug("使用表:{}", name);
        return name;
    }
}
