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

import { getEntities } from './ai-tool-payment.reducer';

export const AiToolPayment = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const aiToolPaymentList = useAppSelector(state => state.aiToolPayment.entities);
  const loading = useAppSelector(state => state.aiToolPayment.loading);

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
      <h2 id="ai-tool-payment-heading" data-cy="AiToolPaymentHeading">
        <Translate contentKey="affliateMarketplaceApp.aiToolPayment.home.title">Ai Tool Payments</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.aiToolPayment.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/ai-tool-payment/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.aiToolPayment.home.createLabel">Create new Ai Tool Payment</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {aiToolPaymentList && aiToolPaymentList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('amount')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.amount">Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amount')} />
                </th>
                <th className="hand" onClick={sort('status')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.status">Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('status')} />
                </th>
                <th className="hand" onClick={sort('paymentLinkUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.paymentLinkUrl">Payment Link Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('paymentLinkUrl')} />
                </th>
                <th className="hand" onClick={sort('pgType')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.pgType">Pg Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pgType')} />
                </th>
                <th className="hand" onClick={sort('pgId')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.pgId">Pg Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pgId')} />
                </th>
                <th className="hand" onClick={sort('pgStatus')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.pgStatus">Pg Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pgStatus')} />
                </th>
                <th className="hand" onClick={sort('pgRequestJson')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.pgRequestJson">Pg Request Json</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pgRequestJson')} />
                </th>
                <th className="hand" onClick={sort('pgResponseJson')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.pgResponseJson">Pg Response Json</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pgResponseJson')} />
                </th>
                <th className="hand" onClick={sort('pgRequestTimeStamp')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.pgRequestTimeStamp">Pg Request Time Stamp</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pgRequestTimeStamp')} />
                </th>
                <th className="hand" onClick={sort('pgResponseTimeStamp')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.pgResponseTimeStamp">Pg Response Time Stamp</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pgResponseTimeStamp')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.aiToolPayment.session">Session</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {aiToolPaymentList.map((aiToolPayment, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/ai-tool-payment/${aiToolPayment.id}`} color="link" size="sm">
                      {aiToolPayment.id}
                    </Button>
                  </td>
                  <td>{aiToolPayment.amount}</td>
                  <td>
                    <Translate contentKey={`affliateMarketplaceApp.PaymentStatus.${aiToolPayment.status}`} />
                  </td>
                  <td>{aiToolPayment.paymentLinkUrl}</td>
                  <td>
                    <Translate contentKey={`affliateMarketplaceApp.PgType.${aiToolPayment.pgType}`} />
                  </td>
                  <td>{aiToolPayment.pgId}</td>
                  <td>{aiToolPayment.pgStatus}</td>
                  <td>{aiToolPayment.pgRequestJson}</td>
                  <td>{aiToolPayment.pgResponseJson}</td>
                  <td>{aiToolPayment.pgRequestTimeStamp}</td>
                  <td>{aiToolPayment.pgResponseTimeStamp}</td>
                  <td>{aiToolPayment.isActive ? 'true' : 'false'}</td>
                  <td>{aiToolPayment.createdBy}</td>
                  <td>
                    {aiToolPayment.createdOn ? (
                      <TextFormat type="date" value={aiToolPayment.createdOn} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{aiToolPayment.updatedBy}</td>
                  <td>
                    {aiToolPayment.updatedOn ? (
                      <TextFormat type="date" value={aiToolPayment.updatedOn} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {aiToolPayment.session ? (
                      <Link to={`/ai-tool-session/${aiToolPayment.session.id}`}>{aiToolPayment.session.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/ai-tool-payment/${aiToolPayment.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/ai-tool-payment/${aiToolPayment.id}/edit`}
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
                        onClick={() => (window.location.href = `/ai-tool-payment/${aiToolPayment.id}/delete`)}
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
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.home.notFound">No Ai Tool Payments found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default AiToolPayment;
