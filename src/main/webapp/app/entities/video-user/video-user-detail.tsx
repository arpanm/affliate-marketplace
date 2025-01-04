import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './video-user.reducer';

export const VideoUserDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const videoUserEntity = useAppSelector(state => state.videoUser.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="videoUserDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.videoUser.detail.title">VideoUser</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{videoUserEntity.id}</dd>
          <dt>
            <span id="userId">
              <Translate contentKey="affliateMarketplaceApp.videoUser.userId">User Id</Translate>
            </span>
          </dt>
          <dd>{videoUserEntity.userId}</dd>
          <dt>
            <span id="userName">
              <Translate contentKey="affliateMarketplaceApp.videoUser.userName">User Name</Translate>
            </span>
          </dt>
          <dd>{videoUserEntity.userName}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="affliateMarketplaceApp.videoUser.name">Name</Translate>
            </span>
          </dt>
          <dd>{videoUserEntity.name}</dd>
          <dt>
            <span id="phone">
              <Translate contentKey="affliateMarketplaceApp.videoUser.phone">Phone</Translate>
            </span>
          </dt>
          <dd>{videoUserEntity.phone}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="affliateMarketplaceApp.videoUser.email">Email</Translate>
            </span>
          </dt>
          <dd>{videoUserEntity.email}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="affliateMarketplaceApp.videoUser.description">Description</Translate>
            </span>
          </dt>
          <dd>{videoUserEntity.description}</dd>
          <dt>
            <span id="imageUrl">
              <Translate contentKey="affliateMarketplaceApp.videoUser.imageUrl">Image Url</Translate>
            </span>
          </dt>
          <dd>{videoUserEntity.imageUrl}</dd>
          <dt>
            <span id="userType">
              <Translate contentKey="affliateMarketplaceApp.videoUser.userType">User Type</Translate>
            </span>
          </dt>
          <dd>{videoUserEntity.userType}</dd>
          <dt>
            <span id="isBlocked">
              <Translate contentKey="affliateMarketplaceApp.videoUser.isBlocked">Is Blocked</Translate>
            </span>
          </dt>
          <dd>{videoUserEntity.isBlocked ? 'true' : 'false'}</dd>
          <dt>
            <span id="blockedTill">
              <Translate contentKey="affliateMarketplaceApp.videoUser.blockedTill">Blocked Till</Translate>
            </span>
          </dt>
          <dd>
            {videoUserEntity.blockedTill ? (
              <TextFormat value={videoUserEntity.blockedTill} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.videoUser.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{videoUserEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.videoUser.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{videoUserEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.videoUser.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {videoUserEntity.createdOn ? <TextFormat value={videoUserEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.videoUser.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{videoUserEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.videoUser.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {videoUserEntity.updatedOn ? <TextFormat value={videoUserEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.videoUser.bank">Bank</Translate>
          </dt>
          <dd>{videoUserEntity.bank ? videoUserEntity.bank.id : ''}</dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.videoUser.company">Company</Translate>
          </dt>
          <dd>{videoUserEntity.company ? videoUserEntity.company.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/video-user" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/video-user/${videoUserEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default VideoUserDetail;
