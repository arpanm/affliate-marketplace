import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getCompetitions } from 'app/entities/competition/competition.reducer';
import { getEntities as getVideoTags } from 'app/entities/video-tag/video-tag.reducer';
import { getEntities as getAffinities } from 'app/entities/affinity/affinity.reducer';
import { getEntities as getVideoUsers } from 'app/entities/video-user/video-user.reducer';
import { UrlType } from 'app/shared/model/enumerations/url-type.model';
import { createEntity, getEntity, reset, updateEntity } from './video-post.reducer';

export const VideoPostUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const competitions = useAppSelector(state => state.competition.entities);
  const videoTags = useAppSelector(state => state.videoTag.entities);
  const affinities = useAppSelector(state => state.affinity.entities);
  const videoUsers = useAppSelector(state => state.videoUser.entities);
  const videoPostEntity = useAppSelector(state => state.videoPost.entity);
  const loading = useAppSelector(state => state.videoPost.loading);
  const updating = useAppSelector(state => state.videoPost.updating);
  const updateSuccess = useAppSelector(state => state.videoPost.updateSuccess);
  const urlTypeValues = Object.keys(UrlType);

  const handleClose = () => {
    navigate('/video-post');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCompetitions({}));
    dispatch(getVideoTags({}));
    dispatch(getAffinities({}));
    dispatch(getVideoUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }

    const entity = {
      ...videoPostEntity,
      ...values,
      competition: competitions.find(it => it.id.toString() === values.competition?.toString()),
      tags: mapIdList(values.tags),
      affinityVectors: mapIdList(values.affinityVectors),
      creator: videoUsers.find(it => it.id.toString() === values.creator?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          urlType: 'YouTube',
          ...videoPostEntity,
          competition: videoPostEntity?.competition?.id,
          tags: videoPostEntity?.tags?.map(e => e.id.toString()),
          affinityVectors: videoPostEntity?.affinityVectors?.map(e => e.id.toString()),
          creator: videoPostEntity?.creator?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.videoPost.home.createOrEditLabel" data-cy="VideoPostCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.videoPost.home.createOrEditLabel">Create or edit a VideoPost</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="video-post-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.title')}
                id="video-post-title"
                name="title"
                data-cy="title"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.description')}
                id="video-post-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.url')}
                id="video-post-url"
                name="url"
                data-cy="url"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.urlType')}
                id="video-post-urlType"
                name="urlType"
                data-cy="urlType"
                type="select"
              >
                {urlTypeValues.map(urlType => (
                  <option value={urlType} key={urlType}>
                    {translate(`affliateMarketplaceApp.UrlType.${urlType}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.isAIGenerated')}
                id="video-post-isAIGenerated"
                name="isAIGenerated"
                data-cy="isAIGenerated"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.isPremium')}
                id="video-post-isPremium"
                name="isPremium"
                data-cy="isPremium"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.isBlocked')}
                id="video-post-isBlocked"
                name="isBlocked"
                data-cy="isBlocked"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.isModerated')}
                id="video-post-isModerated"
                name="isModerated"
                data-cy="isModerated"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.isActive')}
                id="video-post-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.createdBy')}
                id="video-post-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.createdOn')}
                id="video-post-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.updatedBy')}
                id="video-post-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.updatedOn')}
                id="video-post-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                id="video-post-competition"
                name="competition"
                data-cy="competition"
                label={translate('affliateMarketplaceApp.videoPost.competition')}
                type="select"
              >
                <option value="" key="0" />
                {competitions
                  ? competitions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.tags')}
                id="video-post-tags"
                data-cy="tags"
                type="select"
                multiple
                name="tags"
              >
                <option value="" key="0" />
                {videoTags
                  ? videoTags.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPost.affinityVectors')}
                id="video-post-affinityVectors"
                data-cy="affinityVectors"
                type="select"
                multiple
                name="affinityVectors"
              >
                <option value="" key="0" />
                {affinities
                  ? affinities.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="video-post-creator"
                name="creator"
                data-cy="creator"
                label={translate('affliateMarketplaceApp.videoPost.creator')}
                type="select"
              >
                <option value="" key="0" />
                {videoUsers
                  ? videoUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/video-post" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default VideoPostUpdate;
