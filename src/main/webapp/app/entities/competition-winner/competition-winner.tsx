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

import { getEntities } from './competition-winner.reducer';

export const CompetitionWinner = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const competitionWinnerList = useAppSelector(state => state.competitionWinner.entities);
  const loading = useAppSelector(state => state.competitionWinner.loading);

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
      <h2 id="competition-winner-heading" data-cy="CompetitionWinnerHeading">
        <Translate contentKey="affliateMarketplaceApp.competitionWinner.home.title">Competition Winners</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.competitionWinner.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/competition-winner/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.competitionWinner.home.createLabel">Create new Competition Winner</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {competitionWinnerList && competitionWinnerList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionWinner.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('prizeTitle')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionWinner.prizeTitle">Prize Title</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('prizeTitle')} />
                </th>
                <th className="hand" onClick={sort('citation')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionWinner.citation">Citation</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('citation')} />
                </th>
                <th className="hand" onClick={sort('certificateUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionWinner.certificateUrl">Certificate Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('certificateUrl')} />
                </th>
                <th className="hand" onClick={sort('selectedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionWinner.selectedBy">Selected By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('selectedBy')} />
                </th>
                <th className="hand" onClick={sort('selectionReason')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionWinner.selectionReason">Selection Reason</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('selectionReason')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionWinner.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionWinner.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionWinner.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionWinner.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.competitionWinner.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.competitionWinner.winningPost">Winning Post</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.competitionWinner.paymentToWinner">Payment To Winner</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.competitionWinner.competitionPrize">Competition Prize</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {competitionWinnerList.map((competitionWinner, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/competition-winner/${competitionWinner.id}`} color="link" size="sm">
                      {competitionWinner.id}
                    </Button>
                  </td>
                  <td>{competitionWinner.prizeTitle}</td>
                  <td>{competitionWinner.citation}</td>
                  <td>{competitionWinner.certificateUrl}</td>
                  <td>{competitionWinner.selectedBy}</td>
                  <td>{competitionWinner.selectionReason}</td>
                  <td>{competitionWinner.isActive ? 'true' : 'false'}</td>
                  <td>{competitionWinner.createdBy}</td>
                  <td>
                    {competitionWinner.createdOn ? (
                      <TextFormat type="date" value={competitionWinner.createdOn} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{competitionWinner.updatedBy}</td>
                  <td>
                    {competitionWinner.updatedOn ? (
                      <TextFormat type="date" value={competitionWinner.updatedOn} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {competitionWinner.winningPost ? (
                      <Link to={`/video-post/${competitionWinner.winningPost.id}`}>{competitionWinner.winningPost.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {competitionWinner.paymentToWinner ? (
                      <Link to={`/competition-payment-to-winner/${competitionWinner.paymentToWinner.id}`}>
                        {competitionWinner.paymentToWinner.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {competitionWinner.competitionPrize ? (
                      <Link to={`/prize/${competitionWinner.competitionPrize.id}`}>{competitionWinner.competitionPrize.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/competition-winner/${competitionWinner.id}`}
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
                        to={`/competition-winner/${competitionWinner.id}/edit`}
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
                        onClick={() => (window.location.href = `/competition-winner/${competitionWinner.id}/delete`)}
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
              <Translate contentKey="affliateMarketplaceApp.competitionWinner.home.notFound">No Competition Winners found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CompetitionWinner;
