import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './video-post.reducer';

export const VideoPostDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const videoPostEntity = useAppSelector(state => state.videoPost.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="videoPostDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.videoPost.detail.title">VideoPost</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{videoPostEntity.id}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="affliateMarketplaceApp.videoPost.title">Title</Translate>
            </span>
          </dt>
          <dd>{videoPostEntity.title}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="affliateMarketplaceApp.videoPost.description">Description</Translate>
            </span>
          </dt>
          <dd>{videoPostEntity.description}</dd>
          <dt>
            <span id="url">
              <Translate contentKey="affliateMarketplaceApp.videoPost.url">Url</Translate>
            </span>
          </dt>
          <dd>{videoPostEntity.url}</dd>
          <dt>
            <span id="urlType">
              <Translate contentKey="affliateMarketplaceApp.videoPost.urlType">Url Type</Translate>
            </span>
          </dt>
          <dd>{videoPostEntity.urlType}</dd>
          <dt>
            <span id="isAIGenerated">
              <Translate contentKey="affliateMarketplaceApp.videoPost.isAIGenerated">Is AI Generated</Translate>
            </span>
          </dt>
          <dd>{videoPostEntity.isAIGenerated ? 'true' : 'false'}</dd>
          <dt>
            <span id="isPremium">
              <Translate contentKey="affliateMarketplaceApp.videoPost.isPremium">Is Premium</Translate>
            </span>
          </dt>
          <dd>{videoPostEntity.isPremium ? 'true' : 'false'}</dd>
          <dt>
            <span id="isBlocked">
              <Translate contentKey="affliateMarketplaceApp.videoPost.isBlocked">Is Blocked</Translate>
            </span>
          </dt>
          <dd>{videoPostEntity.isBlocked ? 'true' : 'false'}</dd>
          <dt>
            <span id="isModerated">
              <Translate contentKey="affliateMarketplaceApp.videoPost.isModerated">Is Moderated</Translate>
            </span>
          </dt>
          <dd>{videoPostEntity.isModerated ? 'true' : 'false'}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.videoPost.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{videoPostEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.videoPost.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{videoPostEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.videoPost.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {videoPostEntity.createdOn ? <TextFormat value={videoPostEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.videoPost.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{videoPostEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.videoPost.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {videoPostEntity.updatedOn ? <TextFormat value={videoPostEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.videoPost.tags">Tags</Translate>
          </dt>
          <dd>
            {videoPostEntity.tags
              ? videoPostEntity.tags.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {videoPostEntity.tags && i === videoPostEntity.tags.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.videoPost.creator">Creator</Translate>
          </dt>
          <dd>{videoPostEntity.creator ? videoPostEntity.creator.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/video-post" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/video-post/${videoPostEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default VideoPostDetail;
