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

import { getEntities } from './competition-payment-to-winner.reducer';

export const CompetitionPaymentToWinner = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const competitionPaymentToWinnerList = useAppSelector(state => state.competitionPaymentToWinner.entities);
  const loading = useAppSelector(state => state.competitionPaymentToWinner.loading);

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
      <h2 id="competition-payment-to-winner-heading" data-cy="CompetitionPaymentToWinnerHeading">
        <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.home.title">Competition Payment To Winners</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/competition-payment-to-winner/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.home.createLabel">
              Create new Competition Payment To Winner
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {competitionPaymentToWinnerList && competitionPaymentToWinnerList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('prizeTitle')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.prizeTitle">Prize Title</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('prizeTitle')} />
                </th>
                <th className="hand" onClick={sort('prizeAmount')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.prizeAmount">Prize Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('prizeAmount')} />
                </th>
                <th className="hand" onClick={sort('tdsAmount')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.tdsAmount">Tds Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tdsAmount')} />
                </th>
                <th className="hand" onClick={sort('tdsCertificateUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.tdsCertificateUrl">
                    Tds Certificate Url
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tdsCertificateUrl')} />
                </th>
                <th className="hand" onClick={sort('otherDeductionAmount')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.otherDeductionAmount">
                    Other Deduction Amount
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('otherDeductionAmount')} />
                </th>
                <th className="hand" onClick={sort('deductionReason')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.deductionReason">Deduction Reason</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('deductionReason')} />
                </th>
                <th className="hand" onClick={sort('deductionJsonData')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.deductionJsonData">
                    Deduction Json Data
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('deductionJsonData')} />
                </th>
                <th className="hand" onClick={sort('deductionCertificateUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.deductionCertificateUrl">
                    Deduction Certificate Url
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('deductionCertificateUrl')} />
                </th>
                <th className="hand" onClick={sort('paidAmount')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.paidAmount">Paid Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('paidAmount')} />
                </th>
                <th className="hand" onClick={sort('transactionId')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.transactionId">Transaction Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('transactionId')} />
                </th>
                <th className="hand" onClick={sort('transactionScreenshotUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.transactionScreenshotUrl">
                    Transaction Screenshot Url
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('transactionScreenshotUrl')} />
                </th>
                <th className="hand" onClick={sort('transactionDate')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.transactionDate">Transaction Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('transactionDate')} />
                </th>
                <th className="hand" onClick={sort('transactionStatus')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.transactionStatus">Transaction Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('transactionStatus')} />
                </th>
                <th className="hand" onClick={sort('paidBy')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.paidBy">Paid By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('paidBy')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {competitionPaymentToWinnerList.map((competitionPaymentToWinner, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/competition-payment-to-winner/${competitionPaymentToWinner.id}`} color="link" size="sm">
                      {competitionPaymentToWinner.id}
                    </Button>
                  </td>
                  <td>{competitionPaymentToWinner.prizeTitle}</td>
                  <td>{competitionPaymentToWinner.prizeAmount}</td>
                  <td>{competitionPaymentToWinner.tdsAmount}</td>
                  <td>{competitionPaymentToWinner.tdsCertificateUrl}</td>
                  <td>{competitionPaymentToWinner.otherDeductionAmount}</td>
                  <td>{competitionPaymentToWinner.deductionReason}</td>
                  <td>{competitionPaymentToWinner.deductionJsonData}</td>
                  <td>{competitionPaymentToWinner.deductionCertificateUrl}</td>
                  <td>{competitionPaymentToWinner.paidAmount}</td>
                  <td>{competitionPaymentToWinner.transactionId}</td>
                  <td>{competitionPaymentToWinner.transactionScreenshotUrl}</td>
                  <td>
                    {competitionPaymentToWinner.transactionDate ? (
                      <TextFormat type="date" value={competitionPaymentToWinner.transactionDate} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    <Translate contentKey={`affliateMarketplaceApp.TransactionStatus.${competitionPaymentToWinner.transactionStatus}`} />
                  </td>
                  <td>{competitionPaymentToWinner.paidBy}</td>
                  <td>{competitionPaymentToWinner.isActive ? 'true' : 'false'}</td>
                  <td>{competitionPaymentToWinner.createdBy}</td>
                  <td>
                    {competitionPaymentToWinner.createdOn ? (
                      <TextFormat type="date" value={competitionPaymentToWinner.createdOn} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{competitionPaymentToWinner.updatedBy}</td>
                  <td>
                    {competitionPaymentToWinner.updatedOn ? (
                      <TextFormat type="date" value={competitionPaymentToWinner.updatedOn} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/competition-payment-to-winner/${competitionPaymentToWinner.id}`}
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
                        to={`/competition-payment-to-winner/${competitionPaymentToWinner.id}/edit`}
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
                        onClick={() => (window.location.href = `/competition-payment-to-winner/${competitionPaymentToWinner.id}/delete`)}
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
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.home.notFound">
                No Competition Payment To Winners found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CompetitionPaymentToWinner;
