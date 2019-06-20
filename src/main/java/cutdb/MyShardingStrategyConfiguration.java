package cutdb;

import io.shardingjdbc.core.api.config.strategy.ShardingStrategyConfiguration;
import io.shardingjdbc.core.routing.strategy.ShardingStrategy;
import io.shardingjdbc.core.routing.strategy.hint.HintShardingStrategy;

public class MyShardingStrategyConfiguration implements ShardingStrategyConfiguration {
    @Override
    public ShardingStrategy build() {
        //return new MyInlineShardingStrategy();
        return new HintShardingStrategy(new ModuloHintShardingAlgorithm());
    }
}
