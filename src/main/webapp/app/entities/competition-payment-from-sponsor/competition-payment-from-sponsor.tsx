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

import { getEntities } from './competition-payment-from-sponsor.reducer';

export const CompetitionPaymentFromSponsor = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const competitionPaymentFromSponsorList = useAppSelector(state => state.competitionPaymentFromSponsor.entities);
  const loading = useAppSelector(state => state.competitionPaymentFromSponsor.loading);

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
      <h2 id="competition-payment-from-sponsor-heading" data-cy="CompetitionPaymentFromSponsorHeading">
        <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.home.title">
          Competition Payment From Sponsors
        </Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/competition-payment-from-sponsor/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.home.createLabel">
              Create new Competition Payment From Sponsor
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {competitionPaymentFromSponsorList && competitionPaymentFromSponsorList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('amount')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.amount">Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amount')} />
                </th>
                <th className="hand" onClick={sort('transactionId')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.transactionId">Transaction Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('transactionId')} />
                </th>
                <th className="hand" onClick={sort('transactionScreenshotUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.transactionScreenshotUrl">
                    Transaction Screenshot Url
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('transactionScreenshotUrl')} />
                </th>
                <th className="hand" onClick={sort('transactionDate')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.transactionDate">Transaction Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('transactionDate')} />
                </th>
                <th className="hand" onClick={sort('transactionStatus')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.transactionStatus">
                    Transaction Status
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('transactionStatus')} />
                </th>
                <th className="hand" onClick={sort('paidBy')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.paidBy">Paid By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('paidBy')} />
                </th>
                <th className="hand" onClick={sort('paymentReceiptUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.paymentReceiptUrl">
                    Payment Receipt Url
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('paymentReceiptUrl')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.competition">Competition</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {competitionPaymentFromSponsorList.map((competitionPaymentFromSponsor, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/competition-payment-from-sponsor/${competitionPaymentFromSponsor.id}`} color="link" size="sm">
                      {competitionPaymentFromSponsor.id}
                    </Button>
                  </td>
                  <td>{competitionPaymentFromSponsor.amount}</td>
                  <td>{competitionPaymentFromSponsor.transactionId}</td>
                  <td>{competitionPaymentFromSponsor.transactionScreenshotUrl}</td>
                  <td>
                    {competitionPaymentFromSponsor.transactionDate ? (
                      <TextFormat type="date" value={competitionPaymentFromSponsor.transactionDate} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    <Translate contentKey={`affliateMarketplaceApp.TransactionStatus.${competitionPaymentFromSponsor.transactionStatus}`} />
                  </td>
                  <td>{competitionPaymentFromSponsor.paidBy}</td>
                  <td>{competitionPaymentFromSponsor.paymentReceiptUrl}</td>
                  <td>{competitionPaymentFromSponsor.isActive ? 'true' : 'false'}</td>
                  <td>{competitionPaymentFromSponsor.createdBy}</td>
                  <td>
                    {competitionPaymentFromSponsor.createdOn ? (
                      <TextFormat type="date" value={competitionPaymentFromSponsor.createdOn} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{competitionPaymentFromSponsor.updatedBy}</td>
                  <td>
                    {competitionPaymentFromSponsor.updatedOn ? (
                      <TextFormat type="date" value={competitionPaymentFromSponsor.updatedOn} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {competitionPaymentFromSponsor.competition ? (
                      <Link to={`/competition/${competitionPaymentFromSponsor.competition.id}`}>
                        {competitionPaymentFromSponsor.competition.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/competition-payment-from-sponsor/${competitionPaymentFromSponsor.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/competition-payment-from-sponsor/${competitionPaymentFromSponsor.id}/edit`}
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
                        onClick={() =>
                          (window.location.href = `/competition-payment-from-sponsor/${competitionPaymentFromSponsor.id}/delete`)
                        }
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
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.home.notFound">
                No Competition Payment From Sponsors found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CompetitionPaymentFromSponsor;
