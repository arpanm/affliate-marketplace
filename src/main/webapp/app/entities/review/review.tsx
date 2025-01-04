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

import { getEntities } from './review.reducer';

export const Review = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const reviewList = useAppSelector(state => state.review.entities);
  const loading = useAppSelector(state => state.review.loading);

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
      <h2 id="review-heading" data-cy="ReviewHeading">
        <Translate contentKey="affliateMarketplaceApp.review.home.title">Reviews</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.review.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/review/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.review.home.createLabel">Create new Review</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {reviewList && reviewList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.review.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('isLiked')}>
                  <Translate contentKey="affliateMarketplaceApp.review.isLiked">Is Liked</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isLiked')} />
                </th>
                <th className="hand" onClick={sort('isSkipped')}>
                  <Translate contentKey="affliateMarketplaceApp.review.isSkipped">Is Skipped</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isSkipped')} />
                </th>
                <th className="hand" onClick={sort('isDisliked')}>
                  <Translate contentKey="affliateMarketplaceApp.review.isDisliked">Is Disliked</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isDisliked')} />
                </th>
                <th className="hand" onClick={sort('isWatched')}>
                  <Translate contentKey="affliateMarketplaceApp.review.isWatched">Is Watched</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isWatched')} />
                </th>
                <th className="hand" onClick={sort('isFullyWatched')}>
                  <Translate contentKey="affliateMarketplaceApp.review.isFullyWatched">Is Fully Watched</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isFullyWatched')} />
                </th>
                <th className="hand" onClick={sort('isReported')}>
                  <Translate contentKey="affliateMarketplaceApp.review.isReported">Is Reported</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isReported')} />
                </th>
                <th className="hand" onClick={sort('rating')}>
                  <Translate contentKey="affliateMarketplaceApp.review.rating">Rating</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rating')} />
                </th>
                <th className="hand" onClick={sort('comment')}>
                  <Translate contentKey="affliateMarketplaceApp.review.comment">Comment</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('comment')} />
                </th>
                <th className="hand" onClick={sort('reportReason')}>
                  <Translate contentKey="affliateMarketplaceApp.review.reportReason">Report Reason</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('reportReason')} />
                </th>
                <th className="hand" onClick={sort('isBlocked')}>
                  <Translate contentKey="affliateMarketplaceApp.review.isBlocked">Is Blocked</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isBlocked')} />
                </th>
                <th className="hand" onClick={sort('isModerated')}>
                  <Translate contentKey="affliateMarketplaceApp.review.isModerated">Is Moderated</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isModerated')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.review.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.review.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.review.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.review.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.review.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.review.reviewer">Reviewer</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.review.post">Post</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {reviewList.map((review, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/review/${review.id}`} color="link" size="sm">
                      {review.id}
                    </Button>
                  </td>
                  <td>{review.isLiked ? 'true' : 'false'}</td>
                  <td>{review.isSkipped ? 'true' : 'false'}</td>
                  <td>{review.isDisliked ? 'true' : 'false'}</td>
                  <td>{review.isWatched ? 'true' : 'false'}</td>
                  <td>{review.isFullyWatched ? 'true' : 'false'}</td>
                  <td>{review.isReported ? 'true' : 'false'}</td>
                  <td>{review.rating}</td>
                  <td>{review.comment}</td>
                  <td>{review.reportReason}</td>
                  <td>{review.isBlocked ? 'true' : 'false'}</td>
                  <td>{review.isModerated ? 'true' : 'false'}</td>
                  <td>{review.isActive ? 'true' : 'false'}</td>
                  <td>{review.createdBy}</td>
                  <td>{review.createdOn ? <TextFormat type="date" value={review.createdOn} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{review.updatedBy}</td>
                  <td>{review.updatedOn ? <TextFormat type="date" value={review.updatedOn} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{review.reviewer ? <Link to={`/video-user/${review.reviewer.id}`}>{review.reviewer.id}</Link> : ''}</td>
                  <td>{review.post ? <Link to={`/video-post/${review.post.id}`}>{review.post.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/review/${review.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/review/${review.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/review/${review.id}/delete`)}
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
              <Translate contentKey="affliateMarketplaceApp.review.home.notFound">No Reviews found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Review;
