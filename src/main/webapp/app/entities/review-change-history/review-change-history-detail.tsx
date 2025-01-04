import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './review-change-history.reducer';

export const ReviewChangeHistoryDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const reviewChangeHistoryEntity = useAppSelector(state => state.reviewChangeHistory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="reviewChangeHistoryDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.detail.title">ReviewChangeHistory</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.id}</dd>
          <dt>
            <span id="changeType">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.changeType">Change Type</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.changeType}</dd>
          <dt>
            <span id="oldIsLiked">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsLiked">Old Is Liked</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.oldIsLiked ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldIsSkipped">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsSkipped">Old Is Skipped</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.oldIsSkipped ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldIsDisliked">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsDisliked">Old Is Disliked</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.oldIsDisliked ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldIsWatched">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsWatched">Old Is Watched</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.oldIsWatched ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldIsFullyWatched">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsFullyWatched">Old Is Fully Watched</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.oldIsFullyWatched ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldIsReported">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsReported">Old Is Reported</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.oldIsReported ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldRating">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldRating">Old Rating</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.oldRating}</dd>
          <dt>
            <span id="oldComment">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldComment">Old Comment</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.oldComment}</dd>
          <dt>
            <span id="oldReportReason">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldReportReason">Old Report Reason</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.oldReportReason}</dd>
          <dt>
            <span id="oldIsBlocked">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsBlocked">Old Is Blocked</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.oldIsBlocked ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldIsModerated">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsModerated">Old Is Moderated</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.oldIsModerated ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldIsActive">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldIsActive">Old Is Active</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.oldIsActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldCreatedBy">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldCreatedBy">Old Created By</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.oldCreatedBy}</dd>
          <dt>
            <span id="oldCreatedOn">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldCreatedOn">Old Created On</Translate>
            </span>
          </dt>
          <dd>
            {reviewChangeHistoryEntity.oldCreatedOn ? (
              <TextFormat value={reviewChangeHistoryEntity.oldCreatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="oldUpdatedBy">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldUpdatedBy">Old Updated By</Translate>
            </span>
          </dt>
          <dd>{reviewChangeHistoryEntity.oldUpdatedBy}</dd>
          <dt>
            <span id="oldUpdatedOn">
              <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.oldUpdatedOn">Old Updated On</Translate>
            </span>
          </dt>
          <dd>
            {reviewChangeHistoryEntity.oldUpdatedOn ? (
              <TextFormat value={reviewChangeHistoryEntity.oldUpdatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.review">Review</Translate>
          </dt>
          <dd>{reviewChangeHistoryEntity.review ? reviewChangeHistoryEntity.review.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/review-change-history" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/review-change-history/${reviewChangeHistoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ReviewChangeHistoryDetail;
