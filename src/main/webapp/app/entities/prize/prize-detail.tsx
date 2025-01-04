import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './prize.reducer';

export const PrizeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const prizeEntity = useAppSelector(state => state.prize.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="prizeDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.prize.detail.title">Prize</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{prizeEntity.id}</dd>
          <dt>
            <span id="prizeType">
              <Translate contentKey="affliateMarketplaceApp.prize.prizeType">Prize Type</Translate>
            </span>
          </dt>
          <dd>{prizeEntity.prizeType}</dd>
          <dt>
            <span id="prizeTag">
              <Translate contentKey="affliateMarketplaceApp.prize.prizeTag">Prize Tag</Translate>
            </span>
          </dt>
          <dd>{prizeEntity.prizeTag}</dd>
          <dt>
            <span id="prizeDetails">
              <Translate contentKey="affliateMarketplaceApp.prize.prizeDetails">Prize Details</Translate>
            </span>
          </dt>
          <dd>{prizeEntity.prizeDetails}</dd>
          <dt>
            <span id="prizeValueType">
              <Translate contentKey="affliateMarketplaceApp.prize.prizeValueType">Prize Value Type</Translate>
            </span>
          </dt>
          <dd>{prizeEntity.prizeValueType}</dd>
          <dt>
            <span id="prizeValue">
              <Translate contentKey="affliateMarketplaceApp.prize.prizeValue">Prize Value</Translate>
            </span>
          </dt>
          <dd>{prizeEntity.prizeValue}</dd>
          <dt>
            <span id="countOfPossibleWinners">
              <Translate contentKey="affliateMarketplaceApp.prize.countOfPossibleWinners">Count Of Possible Winners</Translate>
            </span>
          </dt>
          <dd>{prizeEntity.countOfPossibleWinners}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.prize.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{prizeEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.prize.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{prizeEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.prize.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>{prizeEntity.createdOn ? <TextFormat value={prizeEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.prize.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{prizeEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.prize.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>{prizeEntity.updatedOn ? <TextFormat value={prizeEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.prize.competition">Competition</Translate>
          </dt>
          <dd>{prizeEntity.competition ? prizeEntity.competition.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/prize" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/prize/${prizeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PrizeDetail;
