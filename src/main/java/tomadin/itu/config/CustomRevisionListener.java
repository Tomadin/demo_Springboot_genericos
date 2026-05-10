package tomadin.itu.config;

import org.hibernate.envers.RevisionListener;
import tomadin.itu.entities.audit.Revision;

public class CustomRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {final Revision revision = (Revision) revisionEntity;}
}
