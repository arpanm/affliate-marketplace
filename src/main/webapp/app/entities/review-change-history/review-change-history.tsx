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

import { getEntities } from './review-change-history.reducer';

export const ReviewChangeHistory = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const reviewChangeHistoryList = useAppSelector(state => state.reviewChangeHistory.entities);
  const loading = useAppSelector(state => state.reviewChangeHistory.loading);

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
      <h2 id="review-change-history-heading" data-cy="ReviewChangeHistoryHeading">
        <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.home.title">Review Change Histories</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/review-change-history/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.home.createLabel">Create new Review Change History</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {reviewChangeHistoryList && reviewChangeHistoryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('changeType')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.changeType">Change Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('changeType')} />
                </th>
                <th className="hand" onClick={sort('oldIsLiked')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsLiked">Old Is Liked</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldIsLiked')} />
                </th>
                <th className="hand" onClick={sort('oldIsSkipped')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsSkipped">Old Is Skipped</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldIsSkipped')} />
                </th>
                <th className="hand" onClick={sort('oldIsDisliked')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsDisliked">Old Is Disliked</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldIsDisliked')} />
                </th>
                <th className="hand" onClick={sort('oldIsWatched')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsWatched">Old Is Watched</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldIsWatched')} />
                </th>
                <th className="hand" onClick={sort('oldIsFullyWatched')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsFullyWatched">Old Is Fully Watched</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldIsFullyWatched')} />
                </th>
                <th className="hand" onClick={sort('oldIsReported')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsReported">Old Is Reported</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldIsReported')} />
                </th>
                <th className="hand" onClick={sort('oldRating')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldRating">Old Rating</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldRating')} />
                </th>
                <th className="hand" onClick={sort('oldComment')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldComment">Old Comment</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldComment')} />
                </th>
                <th className="hand" onClick={sort('oldReportReason')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldReportReason">Old Report Reason</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldReportReason')} />
                </th>
                <th className="hand" onClick={sort('oldIsBlocked')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsBlocked">Old Is Blocked</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldIsBlocked')} />
                </th>
                <th className="hand" onClick={sort('oldIsModerated')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsModerated">Old Is Moderated</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldIsModerated')} />
                </th>
                <th className="hand" onClick={sort('oldIsActive')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsActive">Old Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldIsActive')} />
                </th>
                <th className="hand" onClick={sort('oldCreatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldCreatedBy">Old Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldCreatedBy')} />
                </th>
                <th className="hand" onClick={sort('oldCreatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldCreatedOn">Old Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldCreatedOn')} />
                </th>
                <th className="hand" onClick={sort('oldUpdatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldUpdatedBy">Old Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldUpdatedBy')} />
                </th>
                <th className="hand" onClick={sort('oldUpdatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldUpdatedOn">Old Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldUpdatedOn')} />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.review">Review</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {reviewChangeHistoryList.map((reviewChangeHistory, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/review-change-history/${reviewChangeHistory.id}`} color="link" size="sm">
                      {reviewChangeHistory.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`affliateMarketplaceApp.ChangeType.${reviewChangeHistory.changeType}`} />
                  </td>
                  <td>{reviewChangeHistory.oldIsLiked ? 'true' : 'false'}</td>
                  <td>{reviewChangeHistory.oldIsSkipped ? 'true' : 'false'}</td>
                  <td>{reviewChangeHistory.oldIsDisliked ? 'true' : 'false'}</td>
                  <td>{reviewChangeHistory.oldIsWatched ? 'true' : 'false'}</td>
                  <td>{reviewChangeHistory.oldIsFullyWatched ? 'true' : 'false'}</td>
                  <td>{reviewChangeHistory.oldIsReported ? 'true' : 'false'}</td>
                  <td>{reviewChangeHistory.oldRating}</td>
                  <td>{reviewChangeHistory.oldComment}</td>
                  <td>{reviewChangeHistory.oldReportReason}</td>
                  <td>{reviewChangeHistory.oldIsBlocked ? 'true' : 'false'}</td>
                  <td>{reviewChangeHistory.oldIsModerated ? 'true' : 'false'}</td>
                  <td>{reviewChangeHistory.oldIsActive ? 'true' : 'false'}</td>
                  <td>{reviewChangeHistory.oldCreatedBy}</td>
                  <td>
                    {reviewChangeHistory.oldCreatedOn ? (
                      <TextFormat type="date" value={reviewChangeHistory.oldCreatedOn} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{reviewChangeHistory.oldUpdatedBy}</td>
                  <td>
                    {reviewChangeHistory.oldUpdatedOn ? (
                      <TextFormat type="date" value={reviewChangeHistory.oldUpdatedOn} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {reviewChangeHistory.review ? (
                      <Link to={`/review/${reviewChangeHistory.review.id}`}>{reviewChangeHistory.review.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/review-change-history/${reviewChangeHistory.id}`}
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
                        to={`/review-change-history/${reviewChangeHistory.id}/edit`}
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
                        onClick={() => (window.location.href = `/review-change-history/${reviewChangeHistory.id}/delete`)}
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
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.home.notFound">No Review Change Histories found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default ReviewChangeHistory;
