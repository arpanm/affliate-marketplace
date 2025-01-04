import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './video-tag.reducer';

export const VideoTagDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const videoTagEntity = useAppSelector(state => state.videoTag.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="videoTagDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.videoTag.detail.title">VideoTag</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{videoTagEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="affliateMarketplaceApp.videoTag.name">Name</Translate>
            </span>
          </dt>
          <dd>{videoTagEntity.name}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="affliateMarketplaceApp.videoTag.code">Code</Translate>
            </span>
          </dt>
          <dd>{videoTagEntity.code}</dd>
          <dt>
            <span id="isModerated">
              <Translate contentKey="affliateMarketplaceApp.videoTag.isModerated">Is Moderated</Translate>
            </span>
          </dt>
          <dd>{videoTagEntity.isModerated ? 'true' : 'false'}</dd>
          <dt>
            <span id="isDeleted">
              <Translate contentKey="affliateMarketplaceApp.videoTag.isDeleted">Is Deleted</Translate>
            </span>
          </dt>
          <dd>{videoTagEntity.isDeleted ? 'true' : 'false'}</dd>
          <dt>
            <span id="deletionReason">
              <Translate contentKey="affliateMarketplaceApp.videoTag.deletionReason">Deletion Reason</Translate>
            </span>
          </dt>
          <dd>{videoTagEntity.deletionReason ? 'true' : 'false'}</dd>
          <dt>
            <span id="mergedWithTagName">
              <Translate contentKey="affliateMarketplaceApp.videoTag.mergedWithTagName">Merged With Tag Name</Translate>
            </span>
          </dt>
          <dd>{videoTagEntity.mergedWithTagName}</dd>
          <dt>
            <span id="mergedWithTagCode">
              <Translate contentKey="affliateMarketplaceApp.videoTag.mergedWithTagCode">Merged With Tag Code</Translate>
            </span>
          </dt>
          <dd>{videoTagEntity.mergedWithTagCode}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.videoTag.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{videoTagEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.videoTag.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{videoTagEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.videoTag.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {videoTagEntity.createdOn ? <TextFormat value={videoTagEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.videoTag.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{videoTagEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.videoTag.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {videoTagEntity.updatedOn ? <TextFormat value={videoTagEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.videoTag.posts">Posts</Translate>
          </dt>
          <dd>
            {videoTagEntity.posts
              ? videoTagEntity.posts.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {videoTagEntity.posts && i === videoTagEntity.posts.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/video-tag" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/video-tag/${videoTagEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default VideoTagDetail;
