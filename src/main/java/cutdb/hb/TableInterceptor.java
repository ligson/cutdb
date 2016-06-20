package cutdb.hb;

import org.hibernate.EmptyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by trq on 2016/6/20.
 */
@Component
public class TableInterceptor extends EmptyInterceptor {
    private static Logger logger = LoggerFactory.getLogger(TableInterceptor.class);

    @Override
    public String onPrepareStatement(String sql) {
        logger.debug("sql================={}", sql);
        return super.onPrepareStatement(sql);
    }
}
