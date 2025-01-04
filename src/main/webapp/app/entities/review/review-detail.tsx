import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './review.reducer';

export const ReviewDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const reviewEntity = useAppSelector(state => state.review.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="reviewDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.review.detail.title">Review</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.id}</dd>
          <dt>
            <span id="isLiked">
              <Translate contentKey="affliateMarketplaceApp.review.isLiked">Is Liked</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.isLiked ? 'true' : 'false'}</dd>
          <dt>
            <span id="isSkipped">
              <Translate contentKey="affliateMarketplaceApp.review.isSkipped">Is Skipped</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.isSkipped ? 'true' : 'false'}</dd>
          <dt>
            <span id="isDisliked">
              <Translate contentKey="affliateMarketplaceApp.review.isDisliked">Is Disliked</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.isDisliked ? 'true' : 'false'}</dd>
          <dt>
            <span id="isWatched">
              <Translate contentKey="affliateMarketplaceApp.review.isWatched">Is Watched</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.isWatched ? 'true' : 'false'}</dd>
          <dt>
            <span id="isFullyWatched">
              <Translate contentKey="affliateMarketplaceApp.review.isFullyWatched">Is Fully Watched</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.isFullyWatched ? 'true' : 'false'}</dd>
          <dt>
            <span id="isReported">
              <Translate contentKey="affliateMarketplaceApp.review.isReported">Is Reported</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.isReported ? 'true' : 'false'}</dd>
          <dt>
            <span id="rating">
              <Translate contentKey="affliateMarketplaceApp.review.rating">Rating</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.rating}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="affliateMarketplaceApp.review.comment">Comment</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.comment}</dd>
          <dt>
            <span id="reportReason">
              <Translate contentKey="affliateMarketplaceApp.review.reportReason">Report Reason</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.reportReason}</dd>
          <dt>
            <span id="isBlocked">
              <Translate contentKey="affliateMarketplaceApp.review.isBlocked">Is Blocked</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.isBlocked ? 'true' : 'false'}</dd>
          <dt>
            <span id="isModerated">
              <Translate contentKey="affliateMarketplaceApp.review.isModerated">Is Moderated</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.isModerated ? 'true' : 'false'}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.review.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.review.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.review.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {reviewEntity.createdOn ? <TextFormat value={reviewEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.review.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{reviewEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.review.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {reviewEntity.updatedOn ? <TextFormat value={reviewEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.review.reviewer">Reviewer</Translate>
          </dt>
          <dd>{reviewEntity.reviewer ? reviewEntity.reviewer.id : ''}</dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.review.post">Post</Translate>
          </dt>
          <dd>{reviewEntity.post ? reviewEntity.post.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/review" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/review/${reviewEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ReviewDetail;
