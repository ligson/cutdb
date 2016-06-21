package cutdb.hb;

import cutdb.common.model.AppContext;
import org.hibernate.Session;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * Created by trq on 2016/6/20.
 */
public class TenantIdResolver implements CurrentTenantIdentifierResolver {
    @Override
    public String resolveCurrentTenantIdentifier() {
        return AppContext.currentTenantId;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
