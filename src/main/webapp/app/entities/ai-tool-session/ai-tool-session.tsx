import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { TextFormat, Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './ai-tool-session.reducer';

export const AiToolSession = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const aiToolSessionList = useAppSelector(state => state.aiToolSession.entities);
  const loading = useAppSelector(state => state.aiToolSession.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    }
    return order === ASC ? faSortUp : faSortDown;
  };

  return (
    <div>
      <h2 id="ai-tool-session-heading" data-cy="AiToolSessionHeading">
        <Translate contentKey="affliateMarketplaceApp.aiToolSession.home.title">Ai Tool Sessions</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.aiToolSession.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/ai-tool-session/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.aiToolSession.home.createLabel">Create new Ai Tool Session</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {aiToolSessionList && aiToolSessionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolSession.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('isPaymentLinkGenerated')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolSession.isPaymentLinkGenerated">Is Payment Link Generated</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isPaymentLinkGenerated')} />
                </th>
                <th className="hand" onClick={sort('isPaid')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolSession.isPaid">Is Paid</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isPaid')} />
                </th>
                <th className="hand" onClick={sort('isVideoGenerated')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolSession.isVideoGenerated">Is Video Generated</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isVideoGenerated')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolSession.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolSession.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolSession.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolSession.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolSession.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.aiToolSession.user">User</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.aiToolSession.tool">Tool</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {aiToolSessionList.map((aiToolSession, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/ai-tool-session/${aiToolSession.id}`} color="link" size="sm">
                      {aiToolSession.id}
                    </Button>
                  </td>
                  <td>{aiToolSession.isPaymentLinkGenerated ? 'true' : 'false'}</td>
                  <td>{aiToolSession.isPaid ? 'true' : 'false'}</td>
                  <td>{aiToolSession.isVideoGenerated ? 'true' : 'false'}</td>
                  <td>{aiToolSession.isActive ? 'true' : 'false'}</td>
                  <td>{aiToolSession.createdBy}</td>
                  <td>
                    {aiToolSession.createdOn ? (
                      <TextFormat type="date" value={aiToolSession.createdOn} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{aiToolSession.updatedBy}</td>
                  <td>
                    {aiToolSession.updatedOn ? (
                      <TextFormat type="date" value={aiToolSession.updatedOn} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{aiToolSession.user ? <Link to={`/video-user/${aiToolSession.user.id}`}>{aiToolSession.user.id}</Link> : ''}</td>
                  <td>{aiToolSession.tool ? <Link to={`/ai-tool/${aiToolSession.tool.id}`}>{aiToolSession.tool.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/ai-tool-session/${aiToolSession.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/ai-tool-session/${aiToolSession.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/ai-tool-session/${aiToolSession.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="affliateMarketplaceApp.aiToolSession.home.notFound">No Ai Tool Sessions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default AiToolSession;
