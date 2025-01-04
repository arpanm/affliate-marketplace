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

import { getEntities } from './competition.reducer';

export const Competition = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const competitionList = useAppSelector(state => state.competition.entities);
  const loading = useAppSelector(state => state.competition.loading);

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
      <h2 id="competition-heading" data-cy="CompetitionHeading">
        <Translate contentKey="affliateMarketplaceApp.competition.home.title">Competitions</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.competition.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/competition/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.competition.home.createLabel">Create new Competition</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {competitionList && competitionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('title')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.title">Title</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('title')} />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.description">Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('description')} />
                </th>
                <th className="hand" onClick={sort('status')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.status">Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('status')} />
                </th>
                <th className="hand" onClick={sort('isBlocked')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.isBlocked">Is Blocked</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isBlocked')} />
                </th>
                <th className="hand" onClick={sort('blockReason')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.blockReason">Block Reason</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('blockReason')} />
                </th>
                <th className="hand" onClick={sort('blockedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.blockedBy">Blocked By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('blockedBy')} />
                </th>
                <th className="hand" onClick={sort('isPaused')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.isPaused">Is Paused</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isPaused')} />
                </th>
                <th className="hand" onClick={sort('pauseReason')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.pauseReason">Pause Reason</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pauseReason')} />
                </th>
                <th className="hand" onClick={sort('pausedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.pausedBy">Paused By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pausedBy')} />
                </th>
                <th className="hand" onClick={sort('banner1Url')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.banner1Url">Banner 1 Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('banner1Url')} />
                </th>
                <th className="hand" onClick={sort('banner2Url')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.banner2Url">Banner 2 Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('banner2Url')} />
                </th>
                <th className="hand" onClick={sort('banner3Url')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.banner3Url">Banner 3 Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('banner3Url')} />
                </th>
                <th className="hand" onClick={sort('startDate')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.startDate">Start Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('startDate')} />
                </th>
                <th className="hand" onClick={sort('endDate')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.endDate">End Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('endDate')} />
                </th>
                <th className="hand" onClick={sort('landingUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.landingUrl">Landing Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('landingUrl')} />
                </th>
                <th className="hand" onClick={sort('totalPrizeValue')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.totalPrizeValue">Total Prize Value</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('totalPrizeValue')} />
                </th>
                <th className="hand" onClick={sort('invoiceToSponsorUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.invoiceToSponsorUrl">Invoice To Sponsor Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('invoiceToSponsorUrl')} />
                </th>
                <th className="hand" onClick={sort('tncUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.tncUrl">Tnc Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tncUrl')} />
                </th>
                <th className="hand" onClick={sort('tnc')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.tnc">Tnc</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tnc')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.competition.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.competition.sponsor">Sponsor</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {competitionList.map((competition, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/competition/${competition.id}`} color="link" size="sm">
                      {competition.id}
                    </Button>
                  </td>
                  <td>{competition.title}</td>
                  <td>{competition.description}</td>
                  <td>
                    <Translate contentKey={`affliateMarketplaceApp.CompetitionPaymentStatus.${competition.status}`} />
                  </td>
                  <td>{competition.isBlocked ? 'true' : 'false'}</td>
                  <td>{competition.blockReason}</td>
                  <td>{competition.blockedBy}</td>
                  <td>{competition.isPaused ? 'true' : 'false'}</td>
                  <td>{competition.pauseReason}</td>
                  <td>{competition.pausedBy}</td>
                  <td>{competition.banner1Url}</td>
                  <td>{competition.banner2Url}</td>
                  <td>{competition.banner3Url}</td>
                  <td>
                    {competition.startDate ? <TextFormat type="date" value={competition.startDate} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {competition.endDate ? <TextFormat type="date" value={competition.endDate} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{competition.landingUrl}</td>
                  <td>{competition.totalPrizeValue}</td>
                  <td>{competition.invoiceToSponsorUrl}</td>
                  <td>{competition.tncUrl}</td>
                  <td>{competition.tnc}</td>
                  <td>{competition.isActive ? 'true' : 'false'}</td>
                  <td>{competition.createdBy}</td>
                  <td>
                    {competition.createdOn ? <TextFormat type="date" value={competition.createdOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{competition.updatedBy}</td>
                  <td>
                    {competition.updatedOn ? <TextFormat type="date" value={competition.updatedOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{competition.sponsor ? <Link to={`/sponsor/${competition.sponsor.id}`}>{competition.sponsor.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/competition/${competition.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/competition/${competition.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/competition/${competition.id}/delete`)}
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
              <Translate contentKey="affliateMarketplaceApp.competition.home.notFound">No Competitions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Competition;
