import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './affinity.reducer';

export const AffinityDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const affinityEntity = useAppSelector(state => state.affinity.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="affinityDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.affinity.detail.title">Affinity</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{affinityEntity.id}</dd>
          <dt>
            <span id="segment">
              <Translate contentKey="affliateMarketplaceApp.affinity.segment">Segment</Translate>
            </span>
          </dt>
          <dd>{affinityEntity.segment}</dd>
          <dt>
            <span id="score">
              <Translate contentKey="affliateMarketplaceApp.affinity.score">Score</Translate>
            </span>
          </dt>
          <dd>{affinityEntity.score}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.affinity.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{affinityEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.affinity.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{affinityEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.affinity.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {affinityEntity.createdOn ? <TextFormat value={affinityEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.affinity.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{affinityEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.affinity.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {affinityEntity.updatedOn ? <TextFormat value={affinityEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.affinity.posts">Posts</Translate>
          </dt>
          <dd>
            {affinityEntity.posts
              ? affinityEntity.posts.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {affinityEntity.posts && i === affinityEntity.posts.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.affinity.users">Users</Translate>
          </dt>
          <dd>
            {affinityEntity.users
              ? affinityEntity.users.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {affinityEntity.users && i === affinityEntity.users.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/affinity" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/affinity/${affinityEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AffinityDetail;
