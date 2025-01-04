import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './ai-tool.reducer';

export const AiToolDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const aiToolEntity = useAppSelector(state => state.aiTool.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="aiToolDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.aiTool.detail.title">AiTool</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{aiToolEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="affliateMarketplaceApp.aiTool.name">Name</Translate>
            </span>
          </dt>
          <dd>{aiToolEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="affliateMarketplaceApp.aiTool.description">Description</Translate>
            </span>
          </dt>
          <dd>{aiToolEntity.description}</dd>
          <dt>
            <span id="vendor">
              <Translate contentKey="affliateMarketplaceApp.aiTool.vendor">Vendor</Translate>
            </span>
          </dt>
          <dd>{aiToolEntity.vendor}</dd>
          <dt>
            <span id="linkUrl">
              <Translate contentKey="affliateMarketplaceApp.aiTool.linkUrl">Link Url</Translate>
            </span>
          </dt>
          <dd>{aiToolEntity.linkUrl}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.aiTool.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{aiToolEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.aiTool.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{aiToolEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.aiTool.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {aiToolEntity.createdOn ? <TextFormat value={aiToolEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.aiTool.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{aiToolEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.aiTool.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {aiToolEntity.updatedOn ? <TextFormat value={aiToolEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/ai-tool" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ai-tool/${aiToolEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AiToolDetail;
