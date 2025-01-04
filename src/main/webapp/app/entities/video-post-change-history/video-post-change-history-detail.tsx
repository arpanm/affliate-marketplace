import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './video-post-change-history.reducer';

export const VideoPostChangeHistoryDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const videoPostChangeHistoryEntity = useAppSelector(state => state.videoPostChangeHistory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="videoPostChangeHistoryDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.detail.title">VideoPostChangeHistory</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{videoPostChangeHistoryEntity.id}</dd>
          <dt>
            <span id="changeType">
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.changeType">Change Type</Translate>
            </span>
          </dt>
          <dd>{videoPostChangeHistoryEntity.changeType}</dd>
          <dt>
            <span id="oldtitle">
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldtitle">Oldtitle</Translate>
            </span>
          </dt>
          <dd>{videoPostChangeHistoryEntity.oldtitle}</dd>
          <dt>
            <span id="oldDescription">
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldDescription">Old Description</Translate>
            </span>
          </dt>
          <dd>{videoPostChangeHistoryEntity.oldDescription}</dd>
          <dt>
            <span id="oldUrl">
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldUrl">Old Url</Translate>
            </span>
          </dt>
          <dd>{videoPostChangeHistoryEntity.oldUrl}</dd>
          <dt>
            <span id="oldUrlType">
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldUrlType">Old Url Type</Translate>
            </span>
          </dt>
          <dd>{videoPostChangeHistoryEntity.oldUrlType}</dd>
          <dt>
            <span id="oldIsAIGenerated">
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldIsAIGenerated">Old Is AI Generated</Translate>
            </span>
          </dt>
          <dd>{videoPostChangeHistoryEntity.oldIsAIGenerated ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldIsPremium">
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldIsPremium">Old Is Premium</Translate>
            </span>
          </dt>
          <dd>{videoPostChangeHistoryEntity.oldIsPremium ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldIsBlocked">
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldIsBlocked">Old Is Blocked</Translate>
            </span>
          </dt>
          <dd>{videoPostChangeHistoryEntity.oldIsBlocked ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldIsModerated">
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldIsModerated">Old Is Moderated</Translate>
            </span>
          </dt>
          <dd>{videoPostChangeHistoryEntity.oldIsModerated ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldIsActive">
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldIsActive">Old Is Active</Translate>
            </span>
          </dt>
          <dd>{videoPostChangeHistoryEntity.oldIsActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldCreatedBy">
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldCreatedBy">Old Created By</Translate>
            </span>
          </dt>
          <dd>{videoPostChangeHistoryEntity.oldCreatedBy}</dd>
          <dt>
            <span id="oldCreatedOn">
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldCreatedOn">Old Created On</Translate>
            </span>
          </dt>
          <dd>
            {videoPostChangeHistoryEntity.oldCreatedOn ? (
              <TextFormat value={videoPostChangeHistoryEntity.oldCreatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="oldUpdatedBy">
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldUpdatedBy">Old Updated By</Translate>
            </span>
          </dt>
          <dd>{videoPostChangeHistoryEntity.oldUpdatedBy}</dd>
          <dt>
            <span id="oldUpdatedOn">
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldUpdatedOn">Old Updated On</Translate>
            </span>
          </dt>
          <dd>
            {videoPostChangeHistoryEntity.oldUpdatedOn ? (
              <TextFormat value={videoPostChangeHistoryEntity.oldUpdatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.post">Post</Translate>
          </dt>
          <dd>{videoPostChangeHistoryEntity.post ? videoPostChangeHistoryEntity.post.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/video-post-change-history" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/video-post-change-history/${videoPostChangeHistoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default VideoPostChangeHistoryDetail;
