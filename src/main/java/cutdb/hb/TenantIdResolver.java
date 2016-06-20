package cutdb.hb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * Created by trq on 2016/6/20.
 */
public class TenantIdResolver implements CurrentTenantIdentifierResolver {
    private SessionFactory sessionFactory;
    @Override
    public String resolveCurrentTenantIdentifier() {
        return "org1";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
